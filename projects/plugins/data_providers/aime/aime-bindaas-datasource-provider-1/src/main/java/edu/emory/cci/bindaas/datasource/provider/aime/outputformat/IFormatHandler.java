package edu.emory.cci.bindaas.datasource.provider.aime.outputformat;

import java.sql.ResultSet;

import edu.emory.cci.bindaas.framework.model.QueryResult;
import edu.emory.cci.bindaas.datasource.provider.aime.model.OutputFormatProps;
import edu.emory.cci.bindaas.datasource.provider.aime.model.OutputFormatProps.OutputFormat;
import edu.emory.cci.bindaas.datasource.provider.aime.model.OutputFormatProps.QueryType;

public interface IFormatHandler {

	public QueryResult format(OutputFormatProps outputFormatProps,ResultSet queryResult) throws Exception;
	public QueryType getQueryType() ;
	public OutputFormat getOutputFormat();
	public void validate(OutputFormatProps outputFormatProps) throws Exception;
}
