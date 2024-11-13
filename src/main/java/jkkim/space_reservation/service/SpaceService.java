package jkkim.space_reservation.service;


import jkkim.space_reservation.domain.Member;
import jkkim.space_reservation.domain.Space;
import jkkim.space_reservation.repository.MemberRepository;
import jkkim.space_reservation.repository.SpaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaceService {

    private final SpaceRepository spaceRepository;

    public SpaceService(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    /*
    * 전체 space 조회하기
    * */
    public List<Space> findSpaces() {
        return spaceRepository.findAll();
    }

    /*
    * 예약 가능한 space 조회
    * */

    /*
    * 모든 space 조회
    * */
}

