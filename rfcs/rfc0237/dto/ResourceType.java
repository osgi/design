package org.osgi.service.onem2m.dto;

public enum ResourceType {
    accessControlPolicy(1),
    AE(2),
    container(3),
    contentInstance(4),
    CSEBase(5),
    delivery(6),
    eventConfig(7),
    execInstance(8),
    group(9),
    locationPolicy(10),
    m2mServiceSubscriptionProfile(11),
    mgmtCmd(12),
    mgmtObj(13),
    node(14),
    pollingChannel(15),
    remoteCSE(16),
    request(17),
    schedule(18),
    serviceSubscribedAppRule(19),
    serviceSubscribedNode(20),
    statsCollect(21),
    statsConfig(22),
    subscription(23),
    semanticDescriptor(24),
    notificationTargetMgmtPolicyRef(25),
    notificationTargetPolicy(26),
    policyDeletionRules(27),
    flexContainer(28),
    timeSeries(29),
    timeSeriesInstance(30),
    role(31),
    token(32),
    trafficPattern(33),
    dynamicAuthorizationConsultation(34),

    accessControlPolicyAnnc(10001),
    AEAnnc(10002),
    containerAnnc(10003),
    contentInstanceAnnc(10004),
    groupAnnc(10009),
    locationPolicyAnnc(10010),
    mgmtObjAnnc(10013),
    nodeAnnc(10014),
    remoteCSEAnnc(10016),
    scheduleAnnc(10018),
    semanticDescriptorAnnc(10024),
    flexContainerAnnc(10028),
    timeSeriesAnnc(10029),
    timeSeriesInstanceAnnc(10030),
    trafficPatternAnnc(10033),
    dynamicAuthorizationConsultationAnnc(10034),

    latest(-1),
    oldest(-2),
    fanOutPoint(-3),
    pollingChannelURI(-4);

    private int resourceType;

    private ResourceType(int resourceType) {
        this.resourceType = resourceType;
    }


    public int getValue() {
        return resourceType;
    }
}
