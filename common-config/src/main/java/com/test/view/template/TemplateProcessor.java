package com.test.view.template;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class TemplateProcessor {

	private static Logger log= LoggerFactory.getLogger(TemplateProcessor.class);
	
	public String resolveAndProcessTemplate(Map<String,String> data,String templatePath){
		Configuration cfg = new Configuration();
		try {
			Template template = cfg.getTemplate(templatePath);
			Writer out = new StringWriter(); 
			template.process(data, out); 
			return out.toString();
					
		} catch (IOException e) {
			log.error("Error while reading the file",e.getMessage());
		} catch (TemplateException e) {
			log.error("Template exception occured while processing the html file",e.getMessage());
		}
		return null;
	}
	
}
