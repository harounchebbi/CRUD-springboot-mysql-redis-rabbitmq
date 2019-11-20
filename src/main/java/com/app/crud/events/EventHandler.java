package com.app.crud.events;


import com.app.crud.config.WebSocketConfig;
import com.app.crud.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.event.EventListener;
import org.springframework.hateoas.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {
	private Log log = LogFactory.getLog(getClass());
	private final SimpMessagingTemplate simpMessagingTemplate;

	private final EntityLinks entityLinks;

	public EventHandler(SimpMessagingTemplate simpMessagingTemplate, EntityLinks entityLinks) {
		this.simpMessagingTemplate = simpMessagingTemplate;
		this.entityLinks = entityLinks;
	}


	@EventListener
	public void newUser(UserCreationEvent<User> savedUser) {
		log.info("New User created Event");

		this.simpMessagingTemplate.convertAndSend(
				WebSocketConfig.MESSAGE_PREFIX + "/newUser", getPath(savedUser.getUser()));
	}

	@EventListener
	public void deleteUser(UserDeletionEvent<User> deletedUser) {
		log.info("User deleted Event");
		this.simpMessagingTemplate.convertAndSend(
				WebSocketConfig.MESSAGE_PREFIX + "/deleteUser",getPath(deletedUser.getUser()));
	}

	/**
	 * Take an {@link user} and get the URI using Spring Data REST's {@link EntityLinks}.
	 *
	 * @param user
	 */
	private String getPath(User user) {
		return this.entityLinks.linkForSingleResource(user.getClass(),
				user.getId()).toUri().getPath();
	}
}

