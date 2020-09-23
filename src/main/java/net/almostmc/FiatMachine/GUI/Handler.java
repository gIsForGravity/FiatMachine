package net.almostmc.FiatMachine.GUI;

public interface Handler<T> {
    void onEvent(T param);
}
