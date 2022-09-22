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
		EcsFormatter formatter;
		try
		{
			Class.forName("io.quarkus.opentelemetry.runtime.OpenTelemetryUtil");
			formatter = new OpenTelementryEcsFormatter();
		}
		catch (final ClassNotFoundException e)
		{
			formatter = new EcsFormatter();
		}

		formatter.setServiceName(config.ecs.serviceName);
		formatter.setServiceEnvironment(config.ecs.serviceEnvironment);
		formatter.setIncludeOrigin(config.ecs.includeOrigin);
		formatter.setStackTraceAsArray(config.ecs.stackTraceAsArray);

		if (!config.ecs.additionalFields.isEmpty())
		{
			formatter.setAdditionalFields(
					config.ecs.additionalFields.entrySet().stream().map(e -> String.format("%s=%s", e.getKey(), e.getValue())).collect(Collectors.joining(",")));
		}
		return formatter;
	}
}
