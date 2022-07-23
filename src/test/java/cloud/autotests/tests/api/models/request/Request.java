package cloud.autotests.tests.api.models.request;

import lombok.Data;

@Data
public class Request{
	private Variables variables;
	private String query;
	private String operationName;
}