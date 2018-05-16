package utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoDbProvider {
  protected static final String MONGODB_DOT_URI = "mongodb.uri";
  protected static final String MORPHIA_PACKAGE = "models";
  protected static final String ENVIRONMENT = "ENVIRONMENT";
  protected static final String MONGODB_CONNECTION_POOL_SIZE = "mongodb.connection.pool.size";
  protected static final String MONGODB_SOCKET_TIMEOUT_MS = "MONGODB_SOCKET_TIMEOUT_MS";
  protected static final int DEFAULT_CONNECTIONS_PER_HOST = 100;
  protected static final int DEFAULT_SOCKET_TIMEOUT_MS = 10000;
  protected static final String URI_HAS_BEEN_MASKED = "**** URI HAS BEEN MASKED ****";
  protected Logger logger = LoggerFactory.getLogger(MongoDbProvider.class);
  
  public Datastore get()
  {
    String mongoDBUris = "mongodb://test:test123@192.168.99.100:27017/parking";
    
    Morphia morphia = new Morphia();
    morphia.mapPackage("models");
    
    List<ServerAddress> serverAddressesList = new ArrayList<ServerAddress>();
    List<MongoClientURI> mongoClientURIsList = new ArrayList<MongoClientURI>();
    for (String uri : mongoDBUris.split(","))
    {
      MongoClientURI mongoClient = new MongoClientURI(uri);
      mongoClientURIsList.add(mongoClient);
      serverAddressesList.add(new ServerAddress((String)mongoClient.getHosts().get(0)));
    }
    Datastore datastore = getDatastore(morphia, mongoClientURIsList, serverAddressesList);
    datastore.ensureIndexes();
    
    System.out.println("successfully connected to MongoDB " + printSafe(mongoDBUris));
    return datastore;
  }
  
  protected Datastore getDatastore(Morphia morphia, List<MongoClientURI> mongoClientURIsList, List<ServerAddress> addrs)
  {
    if (addrs.size() == 1) {
      return morphia.createDatastore(new MongoClient(
      
        (ServerAddress)addrs.get(0), 
        getMongoCredentials(mongoClientURIsList), 
        getMongoClientOptions((MongoClientURI)mongoClientURIsList.get(0))), 
        
        ((MongoClientURI)mongoClientURIsList.get(0)).getDatabase());
    }
    return morphia.createDatastore(new MongoClient(addrs, 
    
      getMongoCredentials(mongoClientURIsList), 
      getMongoClientOptions((MongoClientURI)mongoClientURIsList.get(0))), 
      
      ((MongoClientURI)mongoClientURIsList.get(0)).getDatabase());
  }
  
  protected MongoClientOptions getMongoClientOptions(MongoClientURI uri)
  {
    MongoClientOptions.Builder builder = MongoClientOptions.builder();
    /*if (!isLocalEnvironment())
    {
      builder.sslEnabled(true).build();
      this.logger.info("force enabling SSL for MongoDB " + printSafe(uri));
      
      builder.sslInvalidHostNameAllowed(false).build();
      this.logger.info("force disallowing invalid hostnames for MongoDB " + printSafe(uri));
      
      builder.socketKeepAlive(true);
      this.logger.info("force enabling socketKeepAlive for MongoDB " + printSafe(uri));
      
      builder.writeConcern(getWriteConcern(uri));
      
      builder.connectionsPerHost(getConnectionsPerHost(uri));
      
      builder.socketTimeout(getSocketTimeout(uri));
    }*/
    return builder.build();
  }
  
  protected int getSocketTimeout(MongoClientURI uri)
  {
    Integer socketTimeoutMs = Integer.valueOf(10000);
    try
    {
      socketTimeoutMs = Integer.valueOf(Integer.parseInt(getEnvOrProperty("MONGODB_SOCKET_TIMEOUT_MS")));
    }
    catch (Exception localException) {}
    this.logger.info("setting socket timout ms  " + socketTimeoutMs + " for MongoDB " + printSafe(uri));
    return socketTimeoutMs.intValue();
  }
  
  protected WriteConcern getWriteConcern(MongoClientURI uri)
  {
    try
    {
      if (uri.getOptions().getWriteConcern().getJournal().booleanValue())
      {
        this.logger.info("enforcing journalled write concern for MongoDB " + printSafe(uri));
        return WriteConcern.JOURNALED;
      }
      this.logger.info("enforcing weak w1 write concern for MongoDB " + printSafe(uri));
      return WriteConcern.W1;
    }
    catch (Exception e)
    {
      this.logger.info("falling back to enforcing journalled write concern for MongoDB " + printSafe(uri));
    }
    return WriteConcern.JOURNALED;
  }
  
  protected int getConnectionsPerHost(MongoClientURI uri)
  {
    Integer connections = Integer.valueOf(100);
    try
    {
      connections = Integer.valueOf(Integer.parseInt(getEnvOrProperty("mongodb.connection.pool.size")));
    }
    catch (Exception localException) {}
    this.logger.info("setting connections per HOST " + connections + " for MongoDB " + printSafe(uri));
    return connections.intValue();
  }
  
  protected List<MongoCredential> getMongoCredentials(List<MongoClientURI> mongoClientURIsList)
  {
    List<MongoCredential> credentialsList = new ArrayList();
    for (MongoClientURI mongoClientURI : mongoClientURIsList)
    {
      MongoCredential credentials = mongoClientURI.getCredentials();
      credentialsList.add(credentials);
    }
    return credentialsList;
  }
  
  protected String printSafe(MongoClientURI uri)
  {
    try
    {
      return printSafe(uri.getURI());
    }
    catch (Exception e) {}
    return "**** URI HAS BEEN MASKED ****";
  }
  
  protected String printSafe(String uri)
  {
    try
    {
      return uri.substring(0, 15) + "**** URI HAS BEEN MASKED ****";
    }
    catch (Exception e) {}
    return "**** URI HAS BEEN MASKED ****";
  }
  
  protected boolean isLocalEnvironment()
  {
    if (getEnvOrProperty("ENVIRONMENT") == null) {
      return true;
    }
    return false;
  }
  
  protected String getEnvOrProperty(String key)
  {
    String value = System.getProperty(key);
    if (value == null) {
      value = System.getenv(key);
    }
    return value;
  }
}
