package jkkim.space_reservation.repository;

import jkkim.space_reservation.domain.Space;

import java.util.*;

/*
 * repository는 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
 * */
public class MemorySpaceRepository implements SpaceRepository {

//    private static Map<int, Space> store = new HashMap<>();
    @Override
    public Space save(Space space) {
        return null;
    }

    @Override
    public Optional<Space> findBySpaceType(int spaceType) {
        return Optional.empty();
    }

    @Override
    public Optional<Space> findBySpaceStatus(int spaceStatus) {
        return Optional.empty();
    }

    @Override
    public List<Space> findAll() {
        return null;
    }
}
