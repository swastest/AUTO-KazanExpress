package cloud.autotests.tests.api.models.respo;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MakeSearch{
	private String queryText;
	private String id;
	private String queryId;
}