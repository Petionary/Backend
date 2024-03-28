package back.petionary.application.mypet.controller;

import back.petionary.application.mypet.dto.request.MypetCreateRequest;
import back.petionary.application.mypet.dto.request.MypetUpdateRequest;
import back.petionary.application.mypet.dto.response.MypetListResponse;
import back.petionary.application.mypet.dto.response.MypetResponse;
import back.petionary.application.mypet.service.MypetReadService;
import back.petionary.application.mypet.service.MypetWriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"마이펫페이지 API"}, description = "mypetController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/mypet")
public class MypetController {
    private final MypetWriteService mypetWriteService;
    private final MypetReadService mypetReadService;

    @PostMapping("{id}")
    @ApiOperation(value = "mypet 펫 등록", notes = "작성한 펫의 정보를 등록합니다.")
    public String create(@PathVariable(value = "id") Long accountId, @RequestBody MypetCreateRequest mypetCreateRequest) {
        return mypetWriteService.create(accountId, mypetCreateRequest);
    }

    @PutMapping("{accountId}/{petId}")
    @ApiOperation(value = "mypet 펫 정보 수정", notes = "작성한 펫의 정보를 수정합니다.")
    public void update(@PathVariable(value = "accountId") Long accountId, @PathVariable(value = "petId") Long petId, @RequestBody MypetUpdateRequest mypetUpdateRequest){
        mypetWriteService.update(accountId, petId, mypetUpdateRequest);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "mypet 펫 정보 조회", notes = "등록된 펫의 목록을 조회합니다.")
    public List<MypetListResponse> getMypets(@PathVariable(value = "id") Long accountId) {
        return mypetReadService.getMypets(accountId);
    }

    @GetMapping("{accountId}/{petId}")
    @ApiOperation(value = "mypet 펫 상세 정보 조회", notes = "해당 id를 가지는 펫의 상세 정보를 보여줍니다.")
    public MypetResponse findMypet(@PathVariable(value = "accountId") Long accountId, @PathVariable(value = "petId") Long petId) {
        return mypetReadService.findMypet(accountId, petId);
    }
}
