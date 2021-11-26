package com.example.demo.services;

import com.example.demo.contract.CreditDto;
import com.example.demo.model.Credit;
import com.example.demo.repositories.CreditRepository;
import com.example.demo.repositories.TimetablePositionsRepository;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditDbService implements ICreditDbService {
    final private Mapper mapper;
    private CreditRepository repository;
    private TimetablePositionsRepository timetableRepository;
    private List<CreditCalculator> calculators;

    public CreditDbService(Mapper mapper, CreditRepository repository, TimetablePositionsRepository timetableRepository, List<CreditCalculator> calculators) {
        this.mapper = mapper;
        this.repository = repository;
        this.timetableRepository = timetableRepository;
        this.calculators = calculators;
    }

    public int saveTimetable(CreditDto creditParameters){
        var calculator = calculators.stream().findFirst().orElse(new DoNothingCreditCalculator());
        var credit = new Credit();
        credit.setTimetable(calculator.getTimetable(creditParameters));
        credit.setAmount(creditParameters.getAmount());
        credit.setFixedFee(creditParameters.getFixedFee());
        credit.setInstallmentCount(creditParameters.getInstallmentCount());
        credit.setPercentage(creditParameters.getPercentage());
        credit.setInstallmentType(creditParameters.getInstallmentType());
        int id = repository.save(credit).getId();
        for (var x :
                credit.getTimetable()) {
            x.setCredit(credit);
            timetableRepository.save(x);
        }
        return id;
    }

    public CreditDto getCredit(int id){
        return mapper.map(repository.getById(id),CreditDto.class);
    }
}
