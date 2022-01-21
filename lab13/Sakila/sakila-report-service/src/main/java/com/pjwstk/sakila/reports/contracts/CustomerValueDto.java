package com.pjwstk.sakila.reports.contracts;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerValueDto<TValue> {
    private TValue value;
    private CustomerDto customer;
}
