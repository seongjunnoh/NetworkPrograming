package org.example.IceBreaking.repository.question;

import org.example.IceBreaking.domain.Question;
import org.example.IceBreaking.domain.TeamInterestsDto;
import org.example.IceBreaking.status.InterestKeyword;
import org.example.IceBreaking.status.QuestionsByInterestKey;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryQuestionRepository implements QuestionRepository {

    private static final String welcomeQuestion = "전화번호를 입력해주세요.";
    private static final Map<InterestKeyword, QuestionsByInterestKey> questionMap = new HashMap<>();
    private final Map<Integer, List<TeamInterestsDto>> teamInterestsMap = new HashMap<>();

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
    public void saveInterestsByTeam(int teamId, String[] interests) {
        // 채팅방에 입장한 user의 관심사 정보를 저장 & 겹치는 것이 있는지 체크
        List<TeamInterestsDto> teamInterestsList = teamInterestsMap.getOrDefault(teamId, new ArrayList<>());

        for (String interest : interests) {
            boolean exist = false;
            for (TeamInterestsDto dto : teamInterestsList) {
                if (dto.getInterestValue().equals(interest)) {
                    dto.setCount(dto.getCount() + 1);
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                teamInterestsList.add(new TeamInterestsDto(interest, 1));
            }
        }

        teamInterestsMap.put(teamId, teamInterestsList);
    }
}
