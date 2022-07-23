package cloud.autotests.tests.api.models.respo;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataA {
	private MakeSearch makeSearch;
}