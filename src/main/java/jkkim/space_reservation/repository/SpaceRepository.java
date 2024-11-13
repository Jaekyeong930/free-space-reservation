package jkkim.space_reservation.repository;

import jkkim.space_reservation.domain.Space;

import java.util.List;
import java.util.Optional;

public interface SpaceRepository {
    
    Space save(Space space);
    // space 타입으로 검색
    Optional<Space> findBySpaceType(int spaceType);
    // space 상태로 검색
    Optional<Space> findBySpaceStatus(boolean spaceStatus);
    // 모든 space 검색
    List<Space> findAll();
}
