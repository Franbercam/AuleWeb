/*
 *  Copyright (c) 2022, Ben Fortuna
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *
 *   o Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *
 *   o Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *
 *   o Neither the name of Ben Fortuna nor the names of any other contributors
 *  may be used to endorse or promote products derived from this software
 *  without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *  "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 *  A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 *  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.util.Strings;
import net.fortuna.ical4j.util.Uris;
import net.fortuna.ical4j.validate.ValidationException;
import net.fortuna.ical4j.validate.ValidationResult;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;

public class Link extends Property {

    private static final String PROPERTY_NAME = "LINK";

    private URI uri;

    private String text;

    public Link() {
        super(PROPERTY_NAME, new Factory());
    }

    public Link(ParameterList aList, String value) throws URISyntaxException {
        super(PROPERTY_NAME, aList, new Factory());
        setValue(value);
    }

    public URI getUri() {
        return uri;
    }

    public String getText() {
        return text;
    }

    @Override
    public String getValue() {
        if (Value.XML_REFERENCE.equals(getParameter(Parameter.VALUE)) ||
                Value.URI.equals(getParameter(Parameter.VALUE))) {
            return Uris.decode(Strings.valueOf(getUri()));
        } else { // if (Value.UID.equals(getParameter(Parameter.VALUE))) {
            return getText();
        }
    }

    @Override
    public void setValue(String aValue) throws URISyntaxException {
        if (Value.TEXT.equals(getParameter(Parameter.VALUE))) {
            this.text = aValue;
            this.uri = null;
        } else {
            this.uri = Uris.create(aValue);
            this.text = null;
        }
    }

    @Override
    public ValidationResult validate() throws ValidationException {
        return null;
    }

    public static class Factory extends Content.Factory implements PropertyFactory<Link> {
        private static final long serialVersionUID = 1L;

        public Factory() {
            super(PROPERTY_NAME);
        }

        @Override
        public Link createProperty(final ParameterList parameters, final String value)
                throws IOException, URISyntaxException, ParseException {
            return new Link(parameters, value);
        }

        @Override
        public Link createProperty() {
            return new Link();
        }
    }
}
