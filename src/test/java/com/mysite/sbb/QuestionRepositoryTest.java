package com.mysite.sbb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    @DisplayName("findAll")
    void t1() {
        List<Question> questionList =  questionRepository.findAll();

        assertEquals(2, questionList.size());

        Question question  = questionList.get(0);
        assertEquals("sbb가 무엇인가요?", question.getSubject());
    }

    @Test
    @DisplayName("findById")
    void t2() {
        Optional<Question> oq =  questionRepository.findById(1);

        if (oq.isPresent()) {
            Question q = oq.get();

            assertEquals("sbb가 무엇인가요?", q.getSubject());
        }
    }
}
