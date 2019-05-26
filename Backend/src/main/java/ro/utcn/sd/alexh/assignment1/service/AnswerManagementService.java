package ro.utcn.sd.alexh.assignment1.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.alexh.assignment1.dto.AnswerDTO;
import ro.utcn.sd.alexh.assignment1.entity.Answer;
import ro.utcn.sd.alexh.assignment1.entity.AnswerVote;
import ro.utcn.sd.alexh.assignment1.entity.Question;
import ro.utcn.sd.alexh.assignment1.exception.AnswerNotFoundException;
import ro.utcn.sd.alexh.assignment1.exception.IllegalUserOperationException;
import ro.utcn.sd.alexh.assignment1.exception.SelfVoteException;
import ro.utcn.sd.alexh.assignment1.persistence.api.RepositoryFactory;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerManagementService {

    private final RepositoryFactory repositoryFactory;

    @Transactional
    public AnswerDTO addAnswer(AnswerDTO answerDTO) {
        Answer answer = new Answer();
        answer.setAnswerId(answerDTO.getAnswerId());
        answer.setUserId(answerDTO.getUserId()); // TODO
        answer.setQuestionId(answerDTO.getQuestionId());
        answer.setText(answerDTO.getText());
        answer.setCreationDateTime(Timestamp.valueOf(answerDTO.getCreationDateTime()));
        answer.setScore(answerDTO.getScore());

        return AnswerDTO.ofEntity(repositoryFactory.createAnswerRepository().save(answer));
    }

    @Transactional
    public AnswerDTO updateAnswer(Answer answer) {
        return AnswerDTO.ofEntity(repositoryFactory.createAnswerRepository().save(answer));
    }

    @Transactional
    public List<AnswerDTO> listAnswers() {
        return repositoryFactory.createAnswerRepository().findAll().stream()
                .map(AnswerDTO::ofEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public void findAnswersForQuestion(List<Question> questionList) {
        for (Question question : questionList) {
            List<Answer> answers = repositoryFactory.createAnswerRepository().collectAnswersForQuestion(question.getQuestionId());
            answers.sort(Comparator.comparing(Answer::getScore).reversed());
            question.setAnswers(answers);
        }
    }

    @Transactional
    public List<Answer> findAnswerForQuestion(Integer questionId) {
        List<Answer> answers = repositoryFactory.createAnswerRepository().collectAnswersForQuestion(questionId);
        answers.sort(Comparator.comparing(Answer::getScore).reversed());
        return answers;
    }

    @Transactional
    public void deleteAnswer(Integer userId, Integer answerId) throws IllegalUserOperationException {
        Optional<Answer> maybeAnswer = repositoryFactory.createAnswerRepository().findById(answerId);
        if (maybeAnswer.isPresent()) {
            Answer answer = maybeAnswer.get();
            if (answer.getUserId().equals(userId)) {
                repositoryFactory.createAnswerRepository().remove(answer);
            }
        } else {
            throw new AnswerNotFoundException();
        }
    }

    @Transactional
    public void deleteAll() {
        listAnswers().forEach(answerDTO -> deleteAnswer(answerDTO.getUserId(), answerDTO.getAnswerId()));
    }

    @Transactional
    public void editAnswer(Integer userId, Integer answerId, String text) {
        Optional<Answer> maybeAnswer = repositoryFactory.createAnswerRepository().findById(answerId);
        if (maybeAnswer.isPresent()) {
            Answer answer = maybeAnswer.get();
            if (answer.getUserId().equals(userId)) {
                answer.setText(text);
                repositoryFactory.createAnswerRepository().save(answer);
            } else {
                throw new IllegalUserOperationException();
            }
        } else {
            throw new AnswerNotFoundException();
        }
    }

    @Transactional
    public AnswerDTO findAnswerById(Integer id) {
        return AnswerDTO.ofEntity(repositoryFactory.createAnswerRepository().findById(id).orElseThrow(AnswerNotFoundException::new));
    }

    @Transactional
    public void addVote(AnswerVote answerVote) {
        if (answerVote.getUserId().equals(findAnswerByIdInside(answerVote.getAnswerId()).getUserId())) {
            throw new SelfVoteException();
        }
        Answer answer = findAnswerByIdInside(answerVote.getAnswerId());
        answer.setScore(answer.getScore() + answerVote.getVote());
        updateAnswer(answer);
    }

    @Transactional
    public void removeVote(AnswerVote answerVote) {
        Answer answer = findAnswerByIdInside(answerVote.getAnswerId());
        if (!answerVote.getUserId().equals(answer.getUserId())) {
            answer.setScore(answer.getScore() - answerVote.getVote());
            updateAnswer(answer);
        }
    }

    private Answer findAnswerByIdInside(Integer id) {
        return repositoryFactory.createAnswerRepository().findById(id).orElseThrow(AnswerNotFoundException::new);
    }
}
