package com.bsu.xmlparsing.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.nio.file.Path;

public class SchemaValidator {
    private static Logger logger = LogManager.getLogger();
    private Path filePath;
    private Path schemaPath;

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public Path getSchemaPath() {
        return schemaPath;
    }

    public void setSchemaPath(Path schemaPath) {
        this.schemaPath = schemaPath;
    }

    public boolean validate() {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        Schema schema;
        try {
            schema = factory.newSchema(schemaPath.toFile());
            Validator validator = schema.newValidator();
            Source source = new StreamSource(filePath.toFile());
            validator.validate(source);
            logger.info("Schema is valid");
            return true;
        } catch (SAXException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
        logger.info("Schema is not valid");
        return false;
    }

}
