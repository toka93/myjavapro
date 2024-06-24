package apiclients;

import apiwrappers.RequestWrapper;
import apiwrappers.ResponseWrapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.lang.reflect.Type;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static configuration.ConfigProperties.*;

public class BaseAPIClient {

	Logger log = LogManager.getLogger(BaseAPIClient.class);

	public <T, K> ResponseWrapper<T[]> getEntityArray(Class<T[]> t, RequestWrapper<K> requestWrapper, String url) {
		ResponseWrapper<T[]> responseWrapper = new ResponseWrapper<>();

		Response response = configureRequest(requestWrapper).when().get(url);

		responseWrapper.setBody(response.as((Type) t));
		responseWrapper.setResponseRaw(response);

		configureResponse(responseWrapper);

		return responseWrapper;
	}

	public <T, K> ResponseWrapper<T> getEntity(Class<T> t, RequestWrapper<K> requestWrapper, String url) {
		ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
		Response response = configureRequest(requestWrapper).when().get(url);

		responseWrapper.setBody(response.as(t));
		responseWrapper.setResponseRaw(response);

		configureResponse(responseWrapper);

		return responseWrapper;
	}

	public <T, K> ResponseWrapper<T> postEntity(Class<T> t, RequestWrapper<K> requestWrapper, String url) {
		ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
		Response response = configureRequest(requestWrapper).when().post(url);

		responseWrapper.setBody(response.as(t));
		responseWrapper.setResponseRaw(response);

		configureResponse(responseWrapper);

		return responseWrapper;
	}

	public <T, K> ResponseWrapper<T> putEntity(Class<T> t, RequestWrapper<K> requestWrapper, String url) {
		ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
		Response response = configureRequest(requestWrapper).when().put(url);

		responseWrapper.setBody(response.as(t));
		responseWrapper.setResponseRaw(response);

		configureResponse(responseWrapper);

		return responseWrapper;
	}

	public <T, K> ResponseWrapper<T> patchEntity(Class<T> t, RequestWrapper<K> requestWrapper, String url) {
		ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
		Response response = configureRequest(requestWrapper).when().patch(url);

		responseWrapper.setBody(response.as(t));
		responseWrapper.setResponseRaw(response);

		configureResponse(responseWrapper);

		return responseWrapper;
	}

	public <T, K> ResponseWrapper<T> deleteEntity(Class<T> t, RequestWrapper<K> requestWrapper, String url) {
		ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
		Response response = configureRequest(requestWrapper).when().delete(url);

		responseWrapper.setBody(response.as(t));
		responseWrapper.setResponseRaw(response);

		configureResponse(responseWrapper);

		return responseWrapper;
	}

	private <T> RequestSpecification configureRequest(RequestWrapper<T> requestWrapper) {
		RequestSpecification requestSpecification = RestAssured.given();

		switch (logReguest()) {
		case "all":
			
			log.info("logs : {}",requestSpecification);
			break;
		case "parameters":

			log.info("logs : {}",requestSpecification.log().parameters());
		break;
		default:
			log.info("logs : {}",requestSpecification.log().method());
		}

		if (requestWrapper.getHeaders() != null) {
			for (String key : requestWrapper.getHeaders().keySet()) {
				requestSpecification.header(key, requestWrapper.getHeaders().get(key));
			}
		}

		if (requestWrapper.getQueryParameters() != null) {
			for (String key : requestWrapper.getQueryParameters().keySet()) {
				requestSpecification.queryParam(key, requestWrapper.getQueryParameters().get(key));
			}
		}

		if (requestWrapper.getBody() != null) {
			requestSpecification.body(requestWrapper.getBody());
		}

		return requestSpecification;
	}

	private <T> void configureResponse(ResponseWrapper<T> responseWrapper) {
String logs="Log :{}";
		switch (logResponse()) {
		case "all":
			String val=responseWrapper.getResponseRaw().asString();
			log.info(logs,val);
			break;
		case "body":
			 val=responseWrapper.getResponseRaw().then().log().body().toString();
			log.info(logs,val);
			break;
		default:
			 val=(responseWrapper.getResponseRaw().then().log().status()).toString();
			log.info(responseWrapper.getResponseRaw().statusCode());
			log.info(logs,val);

		}
	}

}
