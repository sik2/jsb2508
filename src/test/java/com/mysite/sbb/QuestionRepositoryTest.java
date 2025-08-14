package com.mysite.sbb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
        // SELECT * FROM question WHERE id = 1;
        if (oq.isPresent()) {
            Question q = oq.get();

            assertEquals("sbb가 무엇인가요?", q.getSubject());
        }
    }

    @Test
    @DisplayName("findBySubject")
    void t3() {
        Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?").get();
        // SELECT * FROM question WHERE subject = 'sbb가 무엇인가요?'
        assertThat(q.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("findBySubjectAndContent")
    void t4() {
        Question q = this.questionRepository.findBySubjectAndContent(
                "sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.").get();
        // SELECT * FROM question WHERE subject = 'sbb가 무엇인가요?' AND content = 'sbb에 대해서 알고 싶습니다.'
        assertThat(q.getId()).isEqualTo(1);
    }
}
