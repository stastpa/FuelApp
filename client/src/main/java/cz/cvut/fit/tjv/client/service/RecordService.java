package cz.cvut.fit.tjv.client.service;

import cz.cvut.fit.tjv.client.api_client.RecordClient;
import cz.cvut.fit.tjv.client.model.RecordDTO;
import org.springframework.stereotype.Service;

@Service
public class RecordService {
    private RecordClient recordClient;

    public RecordService(RecordClient recordClient) {
        this.recordClient = recordClient;
    }

    public void create(RecordDTO data) {
        recordClient.create(data);
    }

    public void updateRating(Long id, int val) {
        RecordDTO rec = recordClient.getRecordById(id);
        rec.setRating(rec.getRating() + val);
        recordClient.update(id, rec);
    }
}
