package cz.cvut.fit.tjv.client.service;

import cz.cvut.fit.tjv.client.api_client.RecordClient;
import cz.cvut.fit.tjv.client.model.RecordDTO;

public class RecordService {
    private RecordClient recordClient;

    public RecordService(RecordClient recordClient) {
        this.recordClient = recordClient;
    }

    public void create(RecordDTO data) {
        recordClient.create(data);
    }
}
