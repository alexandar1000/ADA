package com.ucl.ADA.model.analysis_request;

import com.ucl.ADA.model.branch.BranchRepository;
import com.ucl.ADA.model.branch.BranchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AnalysisRequestServiceTest {
    @InjectMocks
    private AnalysisRequestService analysisRequestService;

    @Mock
    private AnalysisRequestRepository analysisRequestRepository;

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private BranchService branchService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddAnalysisRequest(){
        AnalysisRequest request = new AnalysisRequest();
        request.setTimestamp(OffsetDateTime.now());

        when(analysisRequestRepository.save(request)).thenReturn(request);
        AnalysisRequest request1 = analysisRequestService.addAnalysisRequest(request);

        verify(analysisRequestRepository).save(request);
        assertThat(request1).isEqualTo(request);
    }
}
