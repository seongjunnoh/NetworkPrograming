package org.example.IceBreaking.repository.question;

import org.example.IceBreaking.domain.Question;
import org.example.IceBreaking.status.InterestKeyword;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryQuestionRepository implements QuestionRepository {

    private static final String welcomeQuestion = "전화번호를 입력해주세요.";
    private static final Map<String, String[]> questionMap = new HashMap<>();
    private final Map<Integer, String[]> interestsMap = new HashMap<>();

    static {
//        questionMap.put(InterestKeyword.SPORTS, )
    }

    @Override
    public Question findWelcomeQuestion() {
        return new Question(welcomeQuestion);
    }

    @Override
    public void saveInterestsByTeam(int teamId, String[] interests) {
        // 채팅방에 입장한 user의 관심사 정보를 저장 & 겹치는 것이 있는지 체크


    }
}
