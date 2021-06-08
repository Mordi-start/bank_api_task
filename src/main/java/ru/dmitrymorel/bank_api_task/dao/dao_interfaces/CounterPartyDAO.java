package ru.dmitrymorel.bank_api_task.dao.dao_interfaces;

import ru.dmitrymorel.bank_api_task.model.CounterParty;

import java.util.List;

public interface CounterPartyDAO {

    void saveCounterParty(CounterParty counterParty);

    List<CounterParty> getAllForUser(int userId);
}
