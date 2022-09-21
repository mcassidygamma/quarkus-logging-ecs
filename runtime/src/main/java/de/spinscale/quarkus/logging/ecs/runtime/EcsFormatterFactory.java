package de.spinscale.quarkus.logging.ecs.runtime;

import java.util.stream.Collectors;

import co.elastic.logging.jboss.logmanager.EcsFormatter;

public final class EcsFormatterFactory
{
	private EcsFormatterFactory()
	{

	}

	public static EcsFormatter newEcsFormatter(final EcsLoggingConfig config)
	{
		final var formatter = new EcsFormatter();
		formatter.setServiceName(config.serviceName);
		formatter.setIncludeOrigin(config.includeOrigin);
		formatter.setStackTraceAsArray(config.stackTraceAsArray);

		if (!config.additionalFields.isEmpty())
		{
			formatter.setAdditionalFields(config.additionalFields.entrySet().stream().map(e -> String.format("%s=%s", e.getKey(), e.getValue())).collect(Collectors.joining(",")));
		}
		return formatter;
	}
}
