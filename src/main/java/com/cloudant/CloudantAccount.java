package com.cloudant;

import static org.lightcouch.internal.CouchDbUtil.createPost;
import static org.lightcouch.internal.URIBuilder.buildUri;

import java.net.URI;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.lightcouch.CouchDbClient;
import org.lightcouch.Replication;
import org.lightcouch.Replicator;

/**
 * 
 * CloudantAccount exposes Cloudant specific features.
 * This class would expose all the public methods of LightCouch's CouchDbClient that we want to expose
 * plus cloudant specific API's
 *
 */
public class CloudantAccount {
	
	private CouchDbClient client;
	
	private String accountName;
	private String loginUsername;
	private String password;

		
	/**
	 * This constructor is for Cloudant users with no LightCouch legacy
	 * Here we will internally construct the CouchDbClient object and use it
	 */
	public CloudantAccount(String account, String loginUsername, String password) {
		super();
		this.accountName = account;
		this.loginUsername = loginUsername;
		this.password = password;
		this.client = new CouchDbClient("https", account + ".cloudant.com", 443, loginUsername, password);
	}
		
	
		
	/**
	 * Generate an API key
	 * @return the generated key and password
	 */
	public ApiKey generateApiKey() {
		CouchDbClient tmp = new CouchDbClient("https", "cloudant.com", 443, loginUsername, password);
		URI uri = buildUri(tmp.getBaseUri()).path("/api/generate_api_key").build();
		return tmp.executeRequest(createPost(uri,""), ApiKey.class);
			
	}
	
	
	/**
	 * 
	 * @param name name of database to access
	 * @param create flag indicating whether to create the database if doesnt exist.
	 * @return Database object
	 */
	public Database database(String name, boolean create) {
		return new Database(this,client.database(name, create));
	}


	/**
	 * @param dbName
	 * @param confirm
	 * @see org.lightcouch.CouchDbClientBase#deleteDB(java.lang.String, java.lang.String)
	 */
	public void deleteDB(String dbName, String confirm) {
		client.deleteDB(dbName, confirm);
	}


	/**
	 * @param dbName
	 * @see org.lightcouch.CouchDbClientBase#createDB(java.lang.String)
	 */
	public void createDB(String dbName) {
		client.createDB(dbName);
	}


	/**
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return client.equals(obj);
	}


	/**
	 * @return
	 * @see org.lightcouch.CouchDbClientBase#getBaseUri()
	 */
	public URI getBaseUri() {
		return client.getBaseUri();
	}


	/**
	 * @return
	 * @see org.lightcouch.CouchDbClientBase#getAllDbs()
	 */
	public List<String> getAllDbs() {
		return client.getAllDbs();
	}


	/**
	 * @return
	 * @see org.lightcouch.CouchDbClientBase#serverVersion()
	 */
	public String serverVersion() {
		return client.serverVersion();
	}


	/**
	 * @return
	 * @see org.lightcouch.CouchDbClientBase#replication()
	 */
	public Replication replication() {
		return client.replication();
	}


	/**
	 * @return
	 * @see org.lightcouch.CouchDbClientBase#replicator()
	 */
	public Replicator replicator() {
		return client.replicator();
	}


	/**
	 * @param request
	 * @return
	 * @see org.lightcouch.CouchDbClientBase#executeRequest(org.apache.http.client.methods.HttpRequestBase)
	 */
	public HttpResponse executeRequest(HttpRequestBase request) {
		return client.executeRequest(request);
	}

	

	/**
	 * 
	 * @see org.lightcouch.CouchDbClient#shutdown()
	 */
	public void shutdown() {
		client.shutdown();
	}


	
	/**
	 * @param count
	 * @return
	 * @see org.lightcouch.CouchDbClientBase#uuids(long)
	 */
	public List<String> uuids(long count) {
		return client.uuids(count);
	}
	
	String getLoginUsername() {
		return loginUsername;
	}
	
	String getPassword() {
		return password;
	}



	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}
	
	
	
	
}
