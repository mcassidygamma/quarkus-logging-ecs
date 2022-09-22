package de.spinscale.quarkus.logging.ecs.runtime;

import static io.quarkus.opentelemetry.runtime.OpenTelemetryUtil.SPAN_ID;
import static io.quarkus.opentelemetry.runtime.OpenTelemetryUtil.TRACE_ID;

import org.jboss.logmanager.ExtLogRecord;

import co.elastic.logging.jboss.logmanager.EcsFormatter;

public class OpenTelementryEcsFormatter extends EcsFormatter
{
	private static final String ECS_SPAN_ID = "span.id";
	private static final String ECS_TRACEID = "trace.id";

	@Override
	public String format(final ExtLogRecord record)
	{
		record.copyMdc();
		if (record.getMdc(TRACE_ID) != null)
		{
			record.putMdc(ECS_TRACEID, record.getMdc(TRACE_ID));
			record.removeMdc(TRACE_ID);
		}
		if (record.getMdc(SPAN_ID) != null)
		{
			record.putMdc(ECS_SPAN_ID, record.getMdc(SPAN_ID));
			record.removeMdc(SPAN_ID);
		}
		return super.format(record);
	}
}
