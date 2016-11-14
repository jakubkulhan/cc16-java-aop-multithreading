package cz.codecamp.aop.service.impl;

import cz.codecamp.aop.service.TransactionManagerInterface;

public class DummyTransactionManager implements TransactionManagerInterface {
    @Override
    public void begin() {
        System.out.println(">>> BEGIN TRANSACTION <<<");
    }

    @Override
    public void commit() {
        System.out.println(">>> COMMIT TRANSACTION <<<");
    }

    @Override
    public void rollback() {
        System.out.println(">>> ROLLBACK TRANSACTION <<<");
    }
}
