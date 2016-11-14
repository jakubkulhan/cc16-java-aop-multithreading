package cz.codecamp.aop.service;

import org.springframework.stereotype.Service;

@Service
public class TimeTrackedService {

    public void iWantToBeTimeTracked(int delay, int n) {
        try {
            Thread.sleep(delay);
            if (n > 0) {
                iWantToBeTimeTracked(delay, n-1);
            }
        } catch (InterruptedException e) {
        }
    }

}
