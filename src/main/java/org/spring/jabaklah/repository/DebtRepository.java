package org.spring.jabaklah.repository;

import org.spring.jabaklah.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DebtRepository extends JpaRepository<Debt, String> {


    String findDebtNameByCodeDebt(String codeDebt);
}
