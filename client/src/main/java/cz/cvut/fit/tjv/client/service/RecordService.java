package cz.cvut.fit.tjv.client.service;

import cz.cvut.fit.tjv.client.api_client.RecordClient;
import cz.cvut.fit.tjv.client.model.AppUserDTO;

public class RecordService {
    private RecordClient recordClient;

    public RecordService(RecordClient recordClient) {
        this.recordClient = recordClient;
    }

    public void create(AppUserDTO data) {
        recordClient.create(data);
    }
}
