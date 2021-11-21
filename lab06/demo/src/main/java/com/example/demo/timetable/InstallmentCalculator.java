package com.example.demo.timetable;

import java.util.ArrayList;
import java.util.List;

public class InstallmentCalculator {

    public List<Installment> calculateInstalments(Timetable t) {
        double capitalToPay, interest, amount, capital;
        List<Installment> installmentList = new ArrayList<>();
        for (int number = 1; number <= t.getInstallmentCount(); number++) {

            switch (t.getInstalmentType()) {
                case "constant" -> interest = t.getFixedFee() * t.getPercentage();
                case "decreasing" -> interest = t.getFixedFee() * (t.getInstallmentCount() - number + 1) * t.getPercentage();
                default -> interest = 0;
            }
            amount = t.getFixedFee() + interest;
            capitalToPay = t.getAmount() - t.getFixedFee() * number;
            capital = (t.getAmount() - capitalToPay) / t.getAmount();
            Installment newInstalment = new Installment(t.getId(), number, capital, interest, t.getFixedFee(), capitalToPay, amount);
            installmentList.add(newInstalment);
        }
        return installmentList;
    }

}
