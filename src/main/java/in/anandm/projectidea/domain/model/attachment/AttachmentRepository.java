/**
 * 
 */
package in.anandm.projectidea.domain.model.attachment;

import java.util.List;

/**
 * @author anandm
 * 
 */
public interface AttachmentRepository {

	List<Attachment> findAttachmentsOfProjectIdea(Long projectIdeaId);

	void saveAttachment(Attachment attachment);

	void removeAttachment(Long id);

	Attachment findAttachment(Long id);
}
