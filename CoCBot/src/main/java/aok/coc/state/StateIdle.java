package aok.coc.state;

import java.util.logging.Logger;

import aok.coc.util.RobotUtils;
import aok.coc.util.coords.Clickable;

public class StateIdle implements State {
	private static final Logger		logger		= Logger.getLogger(StateIdle.class.getName());

	private static final StateIdle	instance	= new StateIdle();

	private StateIdle() {
	}

	@Override
	public void handle(Context context) throws InterruptedException {
		State nextState = null;
		while (true) {
			logger.info("StateIdle");
			if (Thread.interrupted()) {
				throw new InterruptedException("StateIdle is interrupted.");
			}
			if (RobotUtils.isClickableActive(Clickable.BUTTON_ATTACK)) {
				nextState = StateMainMenu.instance();
				break;
			} else if (RobotUtils.isClickableActive(Clickable.BUTTON_NEXT)) {
				nextState = StateAttack.instance();
				break;
			} else if (RobotUtils.isClickableActive(Clickable.BUTTON_FIND_A_MATCH)) {
				nextState = StateFindAMatch.instance();
				break;
			}

			Thread.sleep(1000);
		}

		context.setState(nextState);
	}

	public static StateIdle instance() {
		return instance;
	}

}
