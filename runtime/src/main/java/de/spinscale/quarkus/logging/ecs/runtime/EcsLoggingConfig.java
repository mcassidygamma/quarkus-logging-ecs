/*
 * Copyright [2020] [Alexander Reelsen]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package de.spinscale.quarkus.logging.ecs.runtime;

import java.util.Map;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(phase = ConfigPhase.RUN_TIME, name = "log")
public class EcsLoggingConfig
{
	/**
	 * ECS logging
	 */
	@ConfigItem(name = "ecs")
	EcsConfig ecs;

	@ConfigGroup
	public static class EcsConfig
	{
		/**
		 * Determine whether to enable the JSON console ECS formatting extension, which disables "normal" console formatting.
		 */
		@ConfigItem(defaultValue = "true")
		boolean enable;

		/**
		 * The service name to use. This is mandatory
		 * Becomes "service-name" in the configuration file
		 */
		@ConfigItem(defaultValue = "${quarkus.application.name}")
		String serviceName;

		/**
		 * The service environment to use. This is mandatory
		 * Becomes "service-environment" in the configuration file
		 */
		@ConfigItem(defaultValue = "${quarkus.profile}")
		String serviceEnvironment;

		/**
		 * Determine whether stack traces should be serialized as JSON array.
		 * Becomes "stack-trace-as-array" in the configuration file
		 */
		@ConfigItem(defaultValue = "false")
		boolean stackTraceAsArray;

		/**
		 * Determine whether origins should be looked up, which may slow down the logging
		 * Becomes "include-origin" in the configuration file
		 */
		@ConfigItem(defaultValue = "false")
		boolean includeOrigin;

		/**
		 * Any additional key value pairs you would like to index
		 * Becomes "additional-fields" in the configuration file
		 */
		@ConfigItem
		Map<String, String> additionalFields;
	}

}
