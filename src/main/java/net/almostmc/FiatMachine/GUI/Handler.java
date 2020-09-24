package net.almostmc.FiatMachine.GUI;

@FunctionalInterface
public interface Handler<T> {
    void onEvent(T param);
}
