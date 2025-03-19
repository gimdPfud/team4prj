package com.example.project4team.service;

import com.example.project4team.dto.FreeDTO;
import com.example.project4team.entity.Free;
import com.example.project4team.repository.FreeRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Log4j2
public class FreeServiceImpl implements FreeService{

    @Autowired
    private final FreeRepository freeRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    //등록 기능
    @Override
    public FreeDTO insertFree(FreeDTO freeDTO) {

        Free free = modelMapper.map(freeDTO,Free.class);

        free =
                freeRepository.save(free);
        log.info("Free등록 eneity 저장 됐니???");

        freeDTO = modelMapper.map(free,FreeDTO.class);


        return freeDTO;
    }

    //목록 - 자유게시판 페이징 처리및 목록 리스트화
    @Override
    public List<FreeDTO> listFree(FreeDTO freeDTO, Pageable pageable) {

        //entity 페이지 처리
        Page<Free> freePage =
                freeRepository.pageFree(pageable);

        //엔티지 페이지 처리 list로 변환
        List<Free> freeList =
                freePage.getContent();

        log.info("Free목록 리스트 값 들어 왔니 " + freeList);

        //DTO 배열로 가져오기
        List<FreeDTO> freeDTOList = new ArrayList<>();

        //Entity 배열로 다시 담고 - DTO로 맵빙후에 배열로된 DTO에 변환된 entity 담기
        for (Free free : freeList){

            freeDTO = modelMapper.map(free,FreeDTO.class);

            freeDTOList.add(freeDTO);
        }

        return freeDTOList;
    }

    //읽기 - 자유게시판 pk 값으로 읽기
    @Override
    public FreeDTO readFree(Long fno) {

        //Free entity -
        Optional<Free> optionalFree =
        freeRepository.findById(fno);

        //예외처리 하기
        Free free =
        optionalFree.get();

        log.info("Free읽기 Pk에 포함된 값 들어 왔니 " + free);

        FreeDTO freeDTO = modelMapper.map(free,FreeDTO.class);


        //반환
        return freeDTO;
    }

    //수정
    @Override
    public FreeDTO modifyFree(FreeDTO freeDTO) {

        //entity pk값 찾아오기
        Optional<Free> optionalFree =
        freeRepository.findById(freeDTO.getFreenum());

        //예외 처리 하기
        Free free =
        optionalFree.orElseThrow(EntityExistsException::new);

        //자유게시판 제목 수정
        free.setTitle(freeDTO.getTitle());
        //자유게시판 내용 수정
        free.setContent(freeDTO.getContent());

        log.info("Free수정 DTO 셋 해준값 들어왔니 ?" + free);

        //수정 반환
        return modelMapper.map(free,FreeDTO.class);

    }

    //삭제
    @Override
    public void delFree(Long fno) {


        //삭제 처리 하기
        freeRepository.deleteById(fno);


    }
}
