package cz.codecamp.aop.service;

public interface TransactionManagerInterface {
    void begin();
    void commit();
    void rollback();
}
