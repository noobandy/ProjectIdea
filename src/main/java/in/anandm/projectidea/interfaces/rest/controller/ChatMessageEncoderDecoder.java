/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import in.anandm.projectidea.domain.shared.ApplicationException;

import java.io.IOException;

import org.atmosphere.config.managed.Decoder;
import org.atmosphere.config.managed.Encoder;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

/**
 * @author anandm
 * 
 */
@Component
public class ChatMessageEncoderDecoder implements Encoder<ChatMessage, String>,
		Decoder<String, ChatMessage> {


	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public ChatMessage decode(String s) {

		try {
			return mapper.readValue(s, ChatMessage.class);
		} catch (JsonParseException e) {

			throw new ApplicationException(e);
		} catch (JsonMappingException e) {

			throw new ApplicationException(e);
		} catch (IOException e) {

			throw new ApplicationException(e);
		}
	}

	@Override
	public String encode(ChatMessage s) {

		try {
			String response =  mapper.writeValueAsString(s);
			return response;
		} catch (JsonGenerationException e) {

			throw new ApplicationException(e);
		} catch (JsonMappingException e) {

			throw new ApplicationException(e);
		} catch (IOException e) {

			throw new ApplicationException(e);
		}
	}

}
