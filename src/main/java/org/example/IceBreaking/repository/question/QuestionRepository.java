package org.example.IceBreaking.repository.question;

import org.example.IceBreaking.domain.Question;

public interface QuestionRepository {

    Question findWelcomeQuestion();

    void saveInterestsByTeam(int teamId, String[] interests);

}
