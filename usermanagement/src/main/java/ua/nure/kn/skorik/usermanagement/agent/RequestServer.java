package ua.nure.kn.skorik.usermanagement.agent;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import ua.nure.kn.skorik.usermanagement.User;
import ua.nure.kn.skorik.usermanagement.db.DaoFactory;
import ua.nure.kn.skorik.usermanagement.db.DatabaseException;

public class RequestServer extends CyclicBehaviour {

	@Override
	public void action() {
		ACLMessage message = myAgent.receive();
		if (message != null) {
			if (message.getPerformative() == ACLMessage.REQUEST) {
				myAgent.send(createReply(message));
			} else if (message.getPerformative() == ACLMessage.INFORM) {
				Collection users = parseMessage(message);
				((SearchAgent) myAgent).showUsers(users);
			} else {
				block();
			}
		} else {
			block();
		}
	}

	private ACLMessage createReply(ACLMessage message) {
		ACLMessage reply = message.createReply();
		String content = message.getContent();
		StringTokenizer tokenizer = new StringTokenizer(content, ",");
		int numSubstr = 2;
		if (tokenizer.countTokens() == numSubstr) {
			String firstName = tokenizer.nextToken();
			String lastName = tokenizer.nextToken();
			Collection users = null;
			try {
				users = DaoFactory.getInstance().getUserDao().find(firstName, lastName);
			} catch (DatabaseException e) {
				e.printStackTrace();
				users = new ArrayList();
			}

			StringBuffer buffer = new StringBuffer();
			for (Iterator it = users.iterator(); it.hasNext();) {
				User user = (User) it.next();
				buffer.append(user.getId()).append(",");
				buffer.append(user.getFirstName()).append(",");
				buffer.append(user.getLastName()).append(",");
			}
			reply.setContent(buffer.toString());
		}
		return reply;
	}

	private Collection parseMessage(ACLMessage message) {
		Collection users = new LinkedList();

		String content = message.getContent();
		if (content != null) {
			StringTokenizer tokenizer1 = new StringTokenizer(content, ";");
			while (tokenizer1.hasMoreTokens()) {
				String userInfo = tokenizer1.nextToken();
				StringTokenizer tokenizer2 = new StringTokenizer(userInfo, ",");
				String id = tokenizer2.nextToken();
				String firstName = tokenizer2.nextToken();
				String lastName = tokenizer2.nextToken();
				users.add(new User(new Long(id), firstName, lastName, null));
			}
		}
		return users;
	}

}
