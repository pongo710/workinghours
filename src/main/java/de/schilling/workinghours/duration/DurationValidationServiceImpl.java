package de.schilling.workinghours.duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author David Schilling - davejs92@gmail.com
 */
@Service
public class DurationValidationServiceImpl implements DurationValidationService {

    private final DurationRepository durationRepository;

    @Autowired
    public DurationValidationServiceImpl(DurationRepository durationRepository) {
        this.durationRepository = durationRepository;
    }

    @Override
    public boolean validateAgainstExsitingDurations(Duration duration) {
        List<Duration> durationList = durationRepository.findByUsernameOrderByStartTimeDesc(duration.getUsername());
        for (Duration currentDuration : durationList) {
            if(!currentDuration.getId().equals(duration.getId())) {
                if (currentDuration.getEndTime() == null) {
                    if (!isAfterFirstOrEqual(currentDuration.getStartTime(), duration.getEndTime()) &&
                            !currentDuration.getStartTime().isBefore(duration.getStartTime())) {
                        return false;
                    }
                } else {
                    if (duration.getEndTime() == null) {
                        if (duration.getStartTime().isBefore(currentDuration.getEndTime()) &&
                                !duration.getStartTime().isBefore(currentDuration.getStartTime())) {
                            return false;
                        }
                    } else {
                        if (!isAfterFirstOrEqual(currentDuration.getEndTime(), duration.getStartTime()) &&
                                isAfterFirstOrEqual(currentDuration.getStartTime(), duration.getEndTime())) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean validateDurationLenth(Duration duration) {
        return duration.getDuration() != 0;
    }

    private boolean isAfterFirstOrEqual(LocalDateTime first, LocalDateTime second) {
        return second.isAfter(first) || second.isEqual(first);
    }
}