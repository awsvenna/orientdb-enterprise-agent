package com.orientechnologies.agent.cloud.processor.tasks.backup;

import com.orientechnologies.agent.OEnterpriseAgent;
import com.orientechnologies.agent.cloud.processor.backup.RemoveBackupCommandProcessor;
import com.orientechnologies.agent.cloud.processor.tasks.AbstractRPCTask;
import com.orientechnologies.agent.cloud.processor.tasks.OkEmptyResponse;
import com.orientechnologies.orient.server.OServer;
import com.orientechnologies.orient.server.distributed.ODistributedServerManager;
import com.orientechnologies.orient.server.distributed.operation.NodeOperationResponse;
import com.orientechnologies.orientdb.cloud.protocol.backup.BackupLogRequest;

/**
 * Created by Enrico Risa on 17/01/2018.
 */
public class RemoveBackupTask extends AbstractRPCTask<BackupLogRequest> {

  public RemoveBackupTask() {
  }

  public RemoveBackupTask(BackupLogRequest payload) {
    super(payload);
  }

  @Override
  public NodeOperationResponse execute(OServer iServer, ODistributedServerManager iManager) {

    OEnterpriseAgent agent = iServer.getPluginByClass(OEnterpriseAgent.class);
    RemoveBackupCommandProcessor.removeBackup(agent, getPayload());
    return new OkEmptyResponse();
  }

  @Override
  public int getMessageId() {
    return 12;
  }

  @Override
  protected Class<BackupLogRequest> getPayloadType() {
    return BackupLogRequest.class;
  }
}
