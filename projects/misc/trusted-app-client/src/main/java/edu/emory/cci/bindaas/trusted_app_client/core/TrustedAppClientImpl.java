package edu.emory.cci.bindaas.trusted_app_client.core;

import java.io.IOException;
import java.net.URI;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.emory.cci.bindaas.trusted_app_client.app.exception.ClientException;
import edu.emory.cci.bindaas.trusted_app_client.app.exception.ServerException;

public class TrustedAppClientImpl implements ITrustedAppClient {

	private static Log log = LogFactory.getLog(TrustedAppClientImpl.class);
	private final static long ROUNDOFF_FACTOR = 100000;
	private String baseUrl;
	private String applicationID;
	private String applicationSecretKey;
	private DefaultHttpClient httpClient;
	private JsonParser jsonParser;
	private Gson gson;

	public TrustedAppClientImpl(String baseUrl, String applicationID,
			String applicationSecretKey) {
		this.baseUrl = baseUrl;
		this.applicationID = applicationID;
		this.applicationSecretKey = applicationSecretKey;
		this.httpClient = new DefaultHttpClient();
		this.jsonParser = new JsonParser();
		this.gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	}

	public static String calculateDigestValue(String applicationID,
			String applicationKey, String salt, String username)
			throws Exception {
		long roundoff = System.currentTimeMillis() / ROUNDOFF_FACTOR;
		String prenonce = roundoff + "|" + applicationKey;
		byte[] nonceBytes = MessageDigest.getInstance("SHA-1").digest(
				prenonce.getBytes("UTF-8"));
		String nonce = DatatypeConverter.printBase64Binary(nonceBytes);

		String predigest = String.format("%s|%s|%s|%s", username, nonce,
				applicationID, salt);
		String digest = DatatypeConverter.printBase64Binary(MessageDigest
				.getInstance("SHA-1").digest(predigest.getBytes("UTF-8")));

		log.info(String.format("Digest of (%s,%s,%s,%s,%s) = %s",
				applicationID, applicationKey, salt, username, roundoff + "",
				digest));

		return digest;
	}

	static String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

	public JsonObject getShortLivedAuthenticationToken(String protocol, String username, Integer lifetime)
			throws ServerException, ClientException {
		try {
			String salt = UUID.randomUUID().toString();
			String digest = calculateDigestValue(this.applicationID,
					this.applicationSecretKey, salt, username);

			URI baseUri = new URI(baseUrl);
			URIBuilder uriBuilder = new URIBuilder(baseUri.toString()
					+ "/issueShortLivedAuthenticationToken");
			uriBuilder.addParameter("lifetime", lifetime.toString());
			HttpGet get = new HttpGet(uriBuilder.build());
			get.addHeader("_protocol", protocol);
			get.addHeader("_username", username);
			get.addHeader("_salt", salt);
			get.addHeader("_digest", digest);
			get.addHeader("_applicationID", this.applicationID);
			HttpResponse response = httpClient.execute(get);

			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == 200
					&& response.getEntity().getContent() != null) {
				return serverResponseToJSON(response);
			} else {
				String message = response.getEntity().getContent() != null ? gson.toJson(
						serverResponseToJSON(response)) : "";
				throw new ServerException(statusLine.getStatusCode(), message);

			}
		} catch (ClientException e) {
			throw e;
		} catch (ServerException e) {
			throw e;
		} catch (Exception e) {
			throw new ClientException(e);
		}

	}

	private JsonObject serverResponseToJSON(HttpResponse response)
			throws IllegalStateException, IOException {
		String content = convertStreamToString(response.getEntity()
				.getContent());
		JsonObject serverResponseJson = jsonParser.parse(content)
				.getAsJsonObject();

		return serverResponseJson;
	}

	private static void serverDump(HttpResponse response)
			throws IllegalStateException, IOException {
		StringBuffer serverDump = new StringBuffer();
		serverDump.append("Server Response Dump\nHeaders");
		for (Header h : response.getAllHeaders()) {
			serverDump.append(h.toString()).append("\n");
		}

		if (response.getEntity() != null
				&& response.getEntity().getContent() != null) {
			serverDump.append("\nBody:\n");
			serverDump.append(convertStreamToString(response.getEntity()
					.getContent()));
		}

		log.info(serverDump.toString());
	}

	public JsonObject authorizeNewUser(String protocol, String username, Long epochTimeExpires,
			String comments) throws ServerException, ClientException {
		try {
			String salt = UUID.randomUUID().toString();
			String digest = calculateDigestValue(this.applicationID,
					this.applicationSecretKey, salt, username);

			URI baseUri = new URI(baseUrl);
			URIBuilder uriBuilder = new URIBuilder(baseUri.toString()
					+ "/authorizeUser");

			if (comments != null)
				uriBuilder.addParameter("comments", comments);

			uriBuilder.addParameter("expires", epochTimeExpires.toString());

			HttpGet get = new HttpGet(uriBuilder.build());
			get.addHeader("_protocol", protocol);
			get.addHeader("_username", username);
			get.addHeader("_salt", salt);
			get.addHeader("_digest", digest);
			get.addHeader("_applicationID", this.applicationID);
			HttpResponse response = httpClient.execute(get);

			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == 200
					&& response.getEntity().getContent() != null) {
				return serverResponseToJSON(response);
			} else {
				String message = response.getEntity().getContent() != null ? gson.toJson(
						serverResponseToJSON(response)) : "";
				throw new ServerException(statusLine.getStatusCode(), message);
			}
		} catch (ClientException e) {
			throw e;
		} catch (ServerException e) {
			throw e;
		} catch (Exception e) {
			throw new ClientException(e);
		}

	}

	public JsonObject revokeAccess(String protocol, String username, String comments)
			throws ServerException, ClientException {

		try {
			String salt = UUID.randomUUID().toString();
			String digest = calculateDigestValue(this.applicationID,
					this.applicationSecretKey, salt, username);

			URI baseUri = new URI(baseUrl);
			URIBuilder uriBuilder = new URIBuilder(baseUri.toString()
					+ "/revokeUser");

			if (comments != null)
				uriBuilder.addParameter("comments", comments);

			HttpDelete delete = new HttpDelete(uriBuilder.build());
			delete.addHeader("_protocol", protocol);
			delete.addHeader("_username", username);
			delete.addHeader("_salt", salt);
			delete.addHeader("_digest", digest);
			delete.addHeader("_applicationID", this.applicationID);
			HttpResponse response = httpClient.execute(delete);

			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == 200
					&& response.getEntity().getContent() != null) {
				return serverResponseToJSON(response);
			} else {
				String message = response.getEntity().getContent() != null ? gson.toJson(
						serverResponseToJSON(response)) : "";
				throw new ServerException(statusLine.getStatusCode(), message);
			}
		} catch (ClientException e) {
			throw e;
		} catch (ServerException e) {
			throw e;
		} catch (Exception e) {
			throw new ClientException(e);
		}

	}

	public JsonArray listAuthenticationTokens(String protocol) throws ServerException, ClientException {
		try {
			String salt = UUID.randomUUID().toString();
			String username = UUID.randomUUID().toString(); // username for this
															// method is not
															// required. so
															// assign any random
															// value
			String digest = calculateDigestValue(this.applicationID,
					this.applicationSecretKey, salt, username);

			Map<String, String> protocolToHeaderKey = new HashMap<String, String>();
			protocolToHeaderKey.put("api_key","apiKey");
			protocolToHeaderKey.put("jwt","jwt");

			URI baseUri = new URI(baseUrl);
			URIBuilder uriBuilder = new URIBuilder(baseUri.toString()
					+ "/listAuthenticationTokens");

			HttpGet get = new HttpGet(uriBuilder.build());
			get.addHeader("_protocol", protocol);
			get.addHeader("_username", username);
			get.addHeader("_salt", salt);
			get.addHeader("_digest", digest);
			get.addHeader("_applicationID", this.applicationID);
			HttpResponse response = httpClient.execute(get);

			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == 200
					&& response.getEntity().getContent() != null) {

				JsonObject json = serverResponseToJSON(response);
				JsonArray jsonArray = json.get("authenticationTokens").getAsJsonArray();
				JsonArray authenticationTokens = new JsonArray();
				Iterator<JsonElement> iter = jsonArray.iterator();
				String applicationID = json.get("applicationID").getAsString();
				String applicationName = json.get("applicationName")
						.getAsString();
				while (iter.hasNext()) {
					JsonObject authenticationTokensJson = iter.next().getAsJsonObject();
					JsonObject authenticationToken = new JsonObject();

					authenticationToken.addProperty("value",authenticationTokensJson.get(
							protocolToHeaderKey.get(protocol)).getAsString());
					authenticationToken.addProperty("expires",authenticationTokensJson.get("dateExpires").getAsString());
					authenticationToken.addProperty("applicationName",applicationName);
					authenticationToken.addProperty("applicationID",applicationID);
					authenticationToken.addProperty("username",authenticationTokensJson.get("emailAddress").getAsString());

					authenticationTokens.add(authenticationToken);
				}
				return authenticationTokens;
			} else {
				String message = response.getEntity().getContent() != null ? gson.toJson(
						serverResponseToJSON(response)) : "";
				throw new ServerException(statusLine.getStatusCode(), message);
			}
		} catch (ClientException e) {
			throw e;
		} catch (ServerException e) {
			throw e;
		} catch (Exception e) {
			throw new ClientException(e);
		}

	}

}
