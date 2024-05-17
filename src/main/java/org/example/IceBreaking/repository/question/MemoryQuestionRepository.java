package org.example.IceBreaking.repository.question;

import org.example.IceBreaking.domain.Question;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryQuestionRepository implements QuestionRepository {

    private static final String welcomeQuestion = "전화번호를 입력해주세요.";

    @Override
    public Question findWelcomeQuestion() {
        return new Question(welcomeQuestion);
    }
}
