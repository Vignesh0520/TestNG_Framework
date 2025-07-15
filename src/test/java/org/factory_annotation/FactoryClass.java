package org.factory_annotation;

import org.testng.annotations.Factory;

public class FactoryClass {

	@Factory
	public Object[] createInstances() {
		return new Object[] { new SampleTestClass_For_FactoryAnnotation("standard_user", "secret_sauce"),
				new SampleTestClass_For_FactoryAnnotation("locked_out_user", "secret_sauce"),
				new SampleTestClass_For_FactoryAnnotation("problem_user", "secret_sauce"),
				new SampleTestClass_For_FactoryAnnotation("performance_glitch_user", "secret_sauce") };
	}

}
