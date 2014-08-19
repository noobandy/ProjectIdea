/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import in.anandm.projectidea.domain.model.attachment.Attachment;
import in.anandm.projectidea.domain.model.attachment.AttachmentRepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;

/**
 * @author anandm
 * 
 */
@Repository
public class AttachmentRepositoryImpl extends BaseRepository<Attachment, Long>
		implements AttachmentRepository {

	@Override
	public List<Attachment> findAttachmentsOfProjectIdea(Long projectIdeaId) {
		Search search = new Search(Attachment.class);
		search.addFilter(Filter.equal("projectIdea.id", projectIdeaId));
		return search(search);
	}

	@Override
	public void saveAttachment(Attachment attachment) {
		save(attachment);
	}

	@Override
	public void removeAttachment(Long id) {
		removeById(id);
	}

	@Override
	public Attachment findAttachment(Long id) {

		return find(id);
	}

}
