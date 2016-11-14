package cz.codecamp.aop.service;

import cz.codecamp.aop.aspect.annotation.TaskExecutionTime;
import org.springframework.stereotype.Service;

@Service
public class TimeTrackedService {

	@TaskExecutionTime
	public void iWantToBeTimeTracked(int delay, int n) {
		try {
			Thread.sleep(delay);
			if (n > 0) {
				iWantToBeTimeTracked(delay, n - 1);
			}
		} catch (InterruptedException e) {
		}
	}

}
