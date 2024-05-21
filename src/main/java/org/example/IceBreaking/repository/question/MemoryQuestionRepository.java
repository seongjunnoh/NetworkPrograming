package org.example.IceBreaking.repository.question;

import org.example.IceBreaking.domain.Question;
import org.example.IceBreaking.dto.UserInterestDto;
import org.example.IceBreaking.enumData.InterestKeyword;
import org.example.IceBreaking.enumData.QuestionsByInterestKey;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryQuestionRepository implements QuestionRepository {

    private static final String welcomeQuestion = "전화번호를 입력해주세요.";
    private static final Map<InterestKeyword, QuestionsByInterestKey> questionMap = new HashMap<>();
    private final Map<Integer, Map<String, String[]>> teamInterestsMap = new HashMap<>();

    static {
        questionMap.put(InterestKeyword.SPORTS, QuestionsByInterestKey.SPORTS);
        questionMap.put(InterestKeyword.MOVIESANDDRAMA, QuestionsByInterestKey.MOVIESANDDRAMA);
        questionMap.put(InterestKeyword.MUSIC, QuestionsByInterestKey.MUSIC);
        questionMap.put(InterestKeyword.GAMES, QuestionsByInterestKey.GAMES);
        questionMap.put(InterestKeyword.TRIP, QuestionsByInterestKey.TRIP);
        questionMap.put(InterestKeyword.EATING, QuestionsByInterestKey.EATING);
        questionMap.put(InterestKeyword.LOVE, QuestionsByInterestKey.LOVE);
        questionMap.put(InterestKeyword.PET, QuestionsByInterestKey.PET);
        questionMap.put(InterestKeyword.BALANCEGAMES, QuestionsByInterestKey.BALANCEGAMES);
        questionMap.put(InterestKeyword.IF, QuestionsByInterestKey.IF);
    }

    @Override
    public Question findWelcomeQuestion() {
        return new Question(welcomeQuestion);
    }

    @Override
    public void saveInterestsByTeam(UserInterestDto userInterestDto) {
        // 채팅방에 입장한 user들의 관심사 정보를 저장 & 겹치는 것이 있는지 체크
        // 같은 userId값을 가진 User가 여러번 입장해도 한번만 put -> 이 기능 추가해야함

        String userId = userInterestDto.getUserId();
        int teamId = userInterestDto.getTeamId();
        String[] interests = userInterestDto.getInterests();

        Map<String, String[]> userInterestsMapInTeam = teamInterestsMap.get(teamId);
        if (userInterestsMapInTeam == null) {
            userInterestsMapInTeam = new HashMap<>();
        }

        // userInterestsMapInTeam 에 userId 별로 관심사 키워드 정보 추가
        /**
         * user가 관심사 정보를 수정하고 다시 채팅방 입장시에는 이 정보가 바껴야함 -> 추가 리펙토링 필요
         * dto에 user가 관심사를 수정했는지 아닌지를 check하는 flag 정보가 추가되어야함
         */
        if (!userInterestsMapInTeam.containsKey(userId)) {
            userInterestsMapInTeam.put(userId, interests);
        }

        teamInterestsMap.put(teamId, userInterestsMapInTeam);

        // 검증용
        for (String key : userInterestsMapInTeam.keySet()) {
            System.out.println(key + " : " + Arrays.toString(userInterestsMapInTeam.get(key)));
        }
    }

    @Override
    public Question findQuestionByTeamInterests(int teamId) {
        return new Question("테스트용 예시질문");
    }
}
