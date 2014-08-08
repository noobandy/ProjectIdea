/*
 ******************************************************************
Project: GSSK
File: org.mkcl.egov.igrbarcode.domain.Filter.java
Created By:vishals
Created On:Nov 21, 2011
Copyright (C), 2011. MKCL.
All Rights Reserved.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.

 ******************************************************************
 */
package in.anandm.projectidea.domain.model;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class Filter.
 *
 * @author VIJAYC
 * @version 1.0.0
 */
public class Filter {


    /** The logger. */
    private  Logger logger = LoggerFactory.getLogger(getClass());

    /** The group op. */
    private String groupOp;

    /** The rules. */
    private Rule[] rules;

    /**
     * Instantiates a new filter.
     */
    public Filter() {
    }

    /**
     * Instantiates a new filter.
     *
     * @param groupOp the group op
     * @param rules the rules
     */
    public Filter(final String groupOp, final Rule[] rules) {
        this.groupOp = groupOp;
        this.rules = rules;
    }

    /**
     * Gets the group op.
     *
     * @return the group op
     */
    public String getGroupOp() {
        return groupOp;
    }

    /**
     * Sets the group op.
     *
     * @param groupOp the new group op
     */
    public void setGroupOp(final String groupOp) {
        this.groupOp = groupOp;
    }

    /**
     * Gets the rules.
     *
     * @return the rules
     */
    public Rule[] getRules() {
        return rules;
    }

    /**
     * Sets the rules.
     *
     * @param rules the new rules
     */
    public void setRules(final Rule[] rules) {
        this.rules = rules;
    }

    /**
     * Creates the.
     *
     * @param jsonData the json data
     * @return the filter
     */
    public  Filter create(final String jsonData) {
        Filter filter = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            filter = mapper.readValue(jsonData, Filter.class);
        } catch (IOException e) {
            logger.error("Error Occured in " + this.getClass() + ":" + e);
        }
        return filter;
    }

}
