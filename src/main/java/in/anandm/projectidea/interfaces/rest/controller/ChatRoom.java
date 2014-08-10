/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import java.io.IOException;
import java.util.Date;

import org.atmosphere.config.service.AtmosphereHandlerService;
import org.atmosphere.cpr.AtmosphereResponse;
import org.atmosphere.handler.OnMessage;
import org.atmosphere.interceptor.AtmosphereResourceLifecycleInterceptor;
import org.atmosphere.interceptor.BroadcastOnPostAtmosphereInterceptor;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author Anand
 *
 */
@AtmosphereHandlerService(path="/chat",
interceptors = { AtmosphereResourceLifecycleInterceptor.class,
		BroadcastOnPostAtmosphereInterceptor.class
})
public class ChatRoom extends OnMessage<String> {
	
	private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onMessage(AtmosphereResponse response, String message) throws IOException {
        response.write(mapper.writeValueAsString(mapper.readValue(message, Data.class)));
    }

	 public final static class Data {

	        private String message;
	        private String author;
	        private long sentAt;

	        public Data() {
	            this("","");
	        }

	        public Data(String author, String message) {
	            this.author = author;
	            this.message = message;
	            this.sentAt = new Date().getTime();
	        }

	        public String getMessage() {
	            return message;
	        }

	        public String getAuthor() {
	            return author;
	        }

	        public void setAuthor(String author) {
	            this.author = author;
	        }

	        public void setMessage(String message) {
	            this.message = message;
	        }

			public long getSentAt() {
				return sentAt;
			}

			public void setSentAt(long sentAt) {
				this.sentAt = sentAt;
			}

	        
	    } 
}
